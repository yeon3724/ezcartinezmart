#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import threading # 멀티스레드를 사용하기 위한 라이브러리
import requests # 웹 통신을 위한 라이브러리
import json # 웹 통신으로 받은 데이터를 JSON으로 변환하기 위한 라이브러리
import firebase_admin # 파이어베이스 서버 연동을 위한 라이브러리
from firebase_admin import credentials # 파이어베이스 서버 접속 인증 도구
from firebase_admin import db # 파이어베이스 CRUD 도구
from firebase_admin import storage # 파이어베이스 스토리지 사용 도구
from picamera import PiCamera # 카메라 도구
from time import sleep # 딜레이 도구
import datetime # 날짜, 시간 가져오기 도구
import sys, os # 시스템 명령어를 직접 사용하기 위한 도구
from uuid import uuid4 # 토큰 생성 도구
import RPi.GPIO as GPIO # 라즈베리파이 GPIO 입출력 라이브러리
from pynput import keyboard # 바코드 입력(=키보드 액션)을 감지하기 위한 라이브러리
import pygame

c = 0 # 저장된 파일 숫자 카운트하기 위한 전역변수
barcode = "" # 전역변수로 사용할 barcode 변수

def work(): # 서브스레드

    GPIO.setwarnings(False) # Runtime 에러 메세지 출력 무시
    GPIO.setmode(GPIO.BCM) # GPIO 핀 번호 설정
    GPIO.setup(17, GPIO.IN) # 인체 감센서 핀 17 입력 설정

    bucket = storage.bucket() # 기본 버킷 사용

    def fileUpload(file): # 파일을 파이어베이스 업로드

        blob = bucket.blob("bbox_"+MODULE_ID+"/"+file) # 파일 저장 위치, 이름
        new_token = uuid4() # access token 생성
        metadata = {"firebaseStorageDownloadTokens": new_token} # access token이 필요
        blob.metadata = metadata # 메타 데이터 설정
        blob.upload_from_filename(filename="./bbox_"+MODULE_ID+"/"+file, content_type='video/mp4') # 업로드 파일 위치, 타입
        fileDataURL = "https://firebasestorage.googleapis.com/v0/b/" + PROJECT_ID + ".appspot.com/o/bbox_"+MODULE_ID+"%2F"
        fileDataURL = fileDataURL + blob.public_url[77:] + "?alt=media" # 파일 url 주소
        print(fileDataURL) # 파일 url 주소 출력 (디버그 0)

    def execute_camera(): # 카메라 촬영

        basename = "bboxsp_"+MODULE_ID # 기본 파일명
        suffix = datetime.datetime.now().strftime("%Y%m%d_%H%M%S") # 기본 파일명이 중복되지 않도록 날짜 데이터 추가
        t = suffix[:4] + "/" + suffix[4:6] + "/" + suffix[6:8] + " " # 블랙박스에 표시할 날짜 정보 텍스트
        t = t + suffix[9:11] + ":" + suffix[11:13] + ":" + suffix[13:] # 블랙박스에 표시할 시간 정보 텍스트

        filename1 = "_".join([basename, suffix]) + '.h264' # '_'로 연결 / h264 파일명
        filename2 = "_".join([basename, suffix]) + '.mp4' # '_'로 연결 / mp4 파일명

        camera = PiCamera() # 카메라 불러오기
        camera.resolution = (640, 480) # 카메라 화질 설정
        camera.start_preview() # 카메라 미리보기
        sleep(3) # 카메라 조도 설정 대기

        camera.annotate_text = MODULE_ID + " " + t # 날짜/시간 정보 이미지 텍스트
        camera.annotate_text_size = 20 # 날짜/시간 정보 이미지 텍스트 크기
        camera.start_recording(output = "/home/yeon/bbox_"+MODULE_ID+"/"+ filename1) # 동영상 촬영
        camera.wait_recording(6) # 5초간 촬영
        camera.stop_recording() # 촬영 중지
        camera.stop_preview() # 카메라 미리보기 끄기
        camera.close() # 카메라 끄기

        # h264 파일을 mp4 파일로 변환
        os.system("MP4Box -fps 30 -add bbox_"+MODULE_ID+"/" + filename1 + " bbox_"+MODULE_ID+"/" + filename2)
        sleep(1) # 1초간 정지(연속 촬영 방지)
        fileUpload(filename2) # 저장한 사진 파일을 파이어베이스에 업로드
        sleep(1) # 1초간 정지(연속 촬영 방지)

    def clearAll(): # 저장 공간 관리를 위해 폴더에 보관하고 있는 파일 삭제

        global c # 저장된 파일 숫자 카운트하기 위한 c 전역 변수
        c = c + 1 # 파일 숫자 카운트
        print(f"save : {c} (remove : 100)") # 디버그 1

        if c == 5 : # 저장한 영상 파일이 100개가 되면

            os.system("rm /home/yeon/bbox_"+MODULE_ID+"/*") # 해당 위치 폴더 내 모든 파일 삭제
            c = 0 # 파일 숫자 카운트 초기화

    try :

        while False :

            sensor_state = GPIO.input(17)
            print(sensor_state)

            if sensor_state == 1 :

                print('sensing on')  # 디버그 2
                clearAll()
                execute_camera()

            else :

                print('sensing off')  # 디버그 3
                sleep(0.75)

    except KeyboardInterrupt:

        GPIO.cleanup()

if __name__ == "__main__": # 메인스레드

    MODULE_ID = "module20001" # 모듈 ID
    os.system("mkdir -m 777 bbox_"+MODULE_ID) # 블랙박스 영상 저장 폴더 생성
    PROJECT_ID = "easycartinezmart" # 파이어베이스 프로젝트 ID

    # 호스트 IP는 공간이 바뀌었을때 변경 해주어야 한다.
    host = '192.168.250.210:8083'
    url = f'http://{host}'

    basketInfoURL = f"{url}/web/basketlist.do" # need URL
    productInfoURL = f"{url}/web/getproduct.do" # 바코드를 Request GET 방식으로 전달하면, 해당 상품 정보를 돌려주는 API

    # 파이어베이스의 JSON 형태의 인증키(코드 파일과 같은 폴더 내에 저장되어 있음)
    cred = credentials.Certificate(f"{PROJECT_ID}-firebase-adminsdk-1yqn5-332b7a5484.json")
    default_app = firebase_admin.initialize_app(cred, {
        'databaseURL' : f'https://{PROJECT_ID}-default-rtdb.firebaseio.com/', # 사용할 파이어베이스 실시간 데이터베이스 URL
        'storageBucket': f"{PROJECT_ID}.appspot.com" # 사용할 파이어베이스 스토리지 URL
    })

    if ref.child(user).get() is not None

        pygame.init()

        while pygame.mixer.music.get_busy() == True: # mp3 파일 출력이 되고 대기 해주는 코드
            continue # 파일이 정상 출력된 후에는 계속 다음 코드로 연결

        #이지마트의 이지카트 도입안내 음성    
        pygame.mixer.music.load(f'/home/yeon/Downloads/로그인 큐알 완료.mp3') # 파이 게임 안에 믹서에 음악 을 읽어오는 함수 (mp3 파일이 있는 경로를 적어주어야 함)
        pygame.mixer.music.play()

    # 안내 메세지 출력
    for i in range(0,2):
        while pygame.mixer.music.get_busy() == True: # mp3 파일 출력이 되고 대기 해주는 코드
            continue # 파일이 정상 출력된 후에는 계속 다음 코드로 연결

        #이지마트의 이지카트 도입안내 음성    
        pygame.mixer.music.load(f'/home/yeon/Downloads/{i}.mp3') # 파이 게임 안에 믹서에 음악 을 읽어오는 함수 (mp3 파일이 있는 경로를 적어주어야 함)
        pygame.mixer.music.play()

    bbox = threading.Thread(target=work) # 서브스레드 변수 담기
    bbox.start() # 서브스레드 시작

    def on_press(key): # 키 눌림 감지 함수

        global barcode # barcode 변수를 전역변수로 사용

        try: # character 이외의 키가 눌렸을 때 예외 처리를 위함

            if key == keyboard.Key.enter: # enter 키가 눌렸을 때(=하나의 바코드가 완전히 입력되었을 때)

                if barcode[0] == "i" :

                    user_info = barcode.split('^')[0]
                    user_id = user_info[1:]
                    user_name = user_id

                    pygame.init()

                    while pygame.mixer.music.get_busy() == True: # mp3 파일 출력이 되고 대기 해주는 코드
                        continue # 파일이 정상 출력된 후에는 계속 다음 코드로 연결

                    #이지마트의 이지카트 도입안내 음성    
                    pygame.mixer.music.load(f'/home/yeon/Downloads/로그인 큐알 완료.mp3') # 파이 게임 안에 믹서에 음악 을 읽어오는 함수 (mp3 파일이 있는 경로를 적어주어야 함)
                    pygame.mixer.music.play()

                    # user 정보를 날리면 장바구니 리스트 돌려줌
                    # 상품 바코드, 상품 이름, 상품 가격, 상품 이미지, 상품 종류, 상품 정렬
                    # 코드 작성 필요
                    response = requests.get(basketInfoURL + "?mb_id=" + user_id).text
                    response = json.loads(response) # String 형태의 response를 JSON 형태로 변환              

                    for i in response :

                        i['b_check'] = 0
                        i['p_count'] = 1
                        del i['b_date']
                        del i['b_seq']
                        del i['mb_id']
                        del i['p_seq']

                        ref = db.reference(MODULE_ID).child(i['p_barcode']) # 모듈 고유번호로 분류하여 파이어베이스 데이터베이스에 입력하기
                        ref.update(i)

                        print('product insert') # 디버그 4

                    ref = db.reference("users")
                    ref.update({1:{
                                'userId':user_id,
                                'userName':user_name,
                                }})

                    barcode = ""

                else :

                    # barcode 정보를 전달하고 API를 통해 상품 정보를 받아 response 변수에 담기
                    response = requests.get(productInfoURL + "?p_barcode=" + barcode).text
                    response = json.loads(response) # String 형태의 response를 JSON 형태로 변환

                    ref = db.reference(MODULE_ID) # 모듈 고유번호로 분류하여 파이어베이스 데이터베이스에 입력하기

                    if ref.child(barcode).get() is None : # 모듈의 파이어베이스 데이터베이스에 바코드 상품 항목이 없으면

                        ref.update({barcode:{ # 바코드 번호
                                    'p_barcode' : barcode,
                                    'p_name': response['datas'][0]['p_name'], # 상품 이름
                                    'p_price': response['datas'][0]['p_price'], # 상품 가격
                                    'p_image': response['datas'][0]['p_image'], # 상품 이미지
                                    'p_cate': response['datas'][0]['p_cate'], # 상품 종류
                                    'p_sort': response['datas'][0]['p_sort'], # 상품 정렬
                                    'p_count':1, # 상품 수량
                                    'b_check':1 # 구분(찜 상품/결제 대기 상품)
                                    }})

                        print('product insert') # 디버그 4
                        print(barcode) # 디버그 7
                        barcode = "" # barcode 변수 데이터 초기화

                    else : # 모듈의 파이어베이스 데이터베이스에 바코드 상품 항목이 있으면

                        if ref.child(barcode).get()['b_check'] : # 바코드 상품 항목의 구분(찜/결제대기)이 결제대기(True)이면
                            count = int(ref.child(barcode).get()['p_count']) # 해당 바코드 상품의 수량 불러오기
                            ref.child(barcode).child('p_count').set(count+1) # 해당 바코드 상품 수량 하나 증가
                            print('count update') # 디버그 5

                        else : # 바코드 상품 항목의 구분(찜/결제대기)이 찜(False)이면
                            ref.child(barcode).child('b_check').set(True) # 바코드 상품 항목의 구분(찜/결제대기)이 결제대기(True)로 변경
                            print('check change update') # 디버그 6

            else:

                barcode = barcode + key.char # 바코드 데이터 하나씩 읽어들이면서 barcode 변수에 추가

        except AttributeError: # character 이외의 키가 눌렸을 때

            print('error') # 'error'를 출력

    with keyboard.Listener(on_press=on_press) as listener: # 키보드 입력 이벤트를 대기하는 리스너

        listener.join()


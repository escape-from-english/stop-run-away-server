# stop-run-away-server

## Endpoint

### Word

<details>
  <summary>펼치기</summary>

#### 영단어 텍스트로 추가

```
POST {api-url}/v1/words/texts

Request
{
  "words" : List<String>
}
```

#### 영단어 엑셀로 추가

- 첫 번째 시트 첫 번째 행의 열에 나열된 단어 추출

```
POST {api-url}/v1/words/excels

Request
{
  "excelFile": MultipartFile
}
```

#### 풀지 않은 문제 랜덤 출제

- 출제된 문제는 푼 문제로 판단

```
GET {api-url}/v1/words/status/not-solved/random

Response
String
```

#### 풀지 않은 문제 존재 여부

```
GET {api-url}/v1/words/status/not-solved/existence

Resposne
Boolean
```

</details>
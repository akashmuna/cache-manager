# CachingManagerAuthToken

This is Spring secured application which generates a JSON Web-Based Token (JWT) after authenticating the user.
It Also has a feature of authorizing the token in order to test the authenticity of the Token

JSON Web Tokens are an open, industry standard RFC 7519 method for representing claims securely between two parties
JWT.IO allows you to decode, verify and generate JWT.

### Endpoints

```bash
http://localhost:8082/authenticate
```

### Sample Calls

Getting Authentication Token using the below sample call

```bash
curl -X POST \
  http://localhost:8082/authenticate \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8082' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'content-length: 47' \
  -d '{
	"username":"admin",
	"password":"password"
}'
```

Sample Response:
```json
	{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2MzYyNDI0MywiaWF0IjoxNTYzNjA2MjQzfQ.jcLc6gNxyV5OdV2QuX5FKSkN4IOyWkcRb1YS4gyGI8tOk-oTkzyyT8UPWVoBl2YgfrTjIIzQYyJ5XQn03kCZBQ"
}
```
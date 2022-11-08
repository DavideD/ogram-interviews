# To run the app: ./gradlew quarkusDev

## To reproduce the error please use postman collection in the root folder (Interviews.postman_collection.json)

### order of API calls:
1) Create Interview (POST /api/interviews)
2) Create Interview's question (POST /api/questions/:interview_id)
3) Get interview (GET /api/interviews/:interview_id)

Same error happens separately for creating question -> creating answer -> fetching question.
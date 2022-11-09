package co.ogram

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.expect
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Order

@QuarkusTest
class InterviewResourceTest {

    @Test
    @Order(1)
    fun testCreateInterview() {
        given()
            .contentType(ContentType.JSON)
            .body(createInterviewBody())
        .`when`()
            .post("/api/interviews")
        .then()
            .statusCode(201)
    }

    @Test
    @Order(2)
    fun testGetExistingInterview() {
        given()
            .contentType(ContentType.JSON)
            .body(createInterviewBody())
        .`when`()
            .post("/api/interviews") // create interview

        expect()
            .statusCode(200)
            .given()
        .`when`()
            .get("/api/interviews/1")
        .then()
            .statusCode(200)
    }

    @Test
    @Order(3)
    fun testGetExistingInterviewWithQuestions() {
        given()
            .contentType(ContentType.JSON)
            .body(createInterviewBody())
        .`when`()
            .post("/api/interviews") // create interview

        given()
            .contentType(ContentType.JSON)
            .body(createQuestionBody())
        .`when`()
            .post("/api/questions/1") // create question for interview

        expect()
            .statusCode(200)
            .given()
        .`when`()
            .get("/api/interviews/1")
            .then()
        .statusCode(200)
            .body("interviewId", equalTo(1))
            .body("jobId", equalTo(1))
            .body("clientId", equalTo(1))
            .body("retakeLimit", equalTo(3))
            .body("questions.size()", equalTo(1))
    }

    @Test
    @Order(4)
    fun testGetExistingInterviewWithQuestionsAndAnswers() {
        given()
            .contentType(ContentType.JSON)
            .body(createInterviewBody())
        .`when`()
            .post("/api/interviews") // create interview

        given()
            .contentType(ContentType.JSON)
            .body(createQuestionBody())
        .`when`()
            .post("/api/questions/1") // create question for interview

        given()
            .contentType(ContentType.JSON)
            .body(createAnswerBody())
        .`when`()
            .post("/api/answers") // create answer for question

        expect()
            .statusCode(200)
            .given()
        .`when`()
            .get("/api/interviews/1")
        .then()
            .statusCode(200)
            .body("interviewId", equalTo(1))
            .body("jobId", equalTo(1))
            .body("clientId", equalTo(1))
            .body("retakeLimit", equalTo(3))
            // Because tables aren't deleted, thre is still a question created by the previous test
            .body("questions.size()", equalTo(2))
    }

    // Helpers:
    private fun createInterviewBody(): MutableMap<String, Any> {
        val body: MutableMap<String, Any> = HashMap()
        body["job_id"] = 1
        body["client_id"] = 1
        body["retake_limit"] = 3

        return body
    }

    private fun createQuestionBody(): MutableMap<String, Any> {
        val body: MutableMap<String, Any> = HashMap()
        body["work_category_id"] = 1
        body["description"] = 1
        body["max_time"] = 3

        return body
    }

    private fun createAnswerBody(): MutableMap<String, Any> {
        val body: MutableMap<String, Any> = HashMap()
        body["question_id"] = 1
        body["sp_id"] = 1
        body["answer_time"] = 60
        body["retake_count"] = 1
        body["total_time"] = 20

        return body
    }
}
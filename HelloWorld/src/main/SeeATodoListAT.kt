class SeeATodoListAT {
    @Test
    fun `List owners can see their lists`() {
        val user = "frank"
        val listName = "shopping"
        val foodToBuy = listOf("carrots", "apples", "milk")
        startTheApplication(user, listName, foodToBuy)
        val list = getToDoList(user, listName)
        expectThat(list.name).isEqualTo(listName)
        expectThat(list.items).isEqualTo(foodToBuy)
    }
}
fun getToDoList(user: String, listName: String): ToDoList {
    val client = JettyClient()
    val request = Request(GET,
        "http://localhost:8081/todo/$user/$listName")
    val response = client(request)
    return if (response.status == Status.OK)
        parseResponse(response)
    else
        fail(response.toMessage())
}
fun parseResponse(html: String): ToDoList = TODO("parse the response")
fun startTheApplication(
    user: String, listName: String, items: List<String>) {
    val server = Zettai().asServer(SunHttp(8081)) //a random port
// todo setup user and list
}
}

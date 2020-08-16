package com.digitalcreative.appmurid.data.entity

data class Assignment(
    val title: String,
    val description: String,
    val sections: List<Section>
) {
    data class Section(
        val section: String,
        val questions: List<Question>
    ) {
        data class Question(
            val question: String,
            val choices: List<Choice>
        ) {
            data class Choice(
                val choice: String
            )
        }
    }
}

enum class TokenType{
    NUMBER,
    PLUS,
    MINUS,
    STAR,
    SLASH,
    OPEN_PARENTHESIS,
    CLOSE_PARENTHESIS,
    SEMICOLON,
    LET,
    IDENTIFIER,
    EQUAL,

    END_OF_FILE
}
data class Token(val type: TokenType, val literal:String="")
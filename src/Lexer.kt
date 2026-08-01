
class Lexer(val input: String) {
    private  var tokens : MutableList<Token> = mutableListOf()
    private var currentPosition = 0
    private fun advance(): Char{
        val returnCharacter :Char = input[currentPosition]
        currentPosition=currentPosition+1
        return returnCharacter
    }
    private  fun isAtEnd():Boolean{
        return currentPosition>=input.length
    }
    fun scanTokens(): List<Token> {
        while (!isAtEnd()){
            scanNextToken()
        }
        tokens.add(Token(TokenType.END_OF_FILE))
        return tokens
    }
    private fun peek():Char{
        if (isAtEnd()){
            return 'd'
        }
        return input[currentPosition]
    }
    private  fun scanNumber(){
        val startingPosition = currentPosition-1
        while (peek().isDigit()){
            advance()
        }
        tokens.add(Token(TokenType.NUMBER,input.substring(startingPosition,currentPosition)))
    }
    private fun scanIdentifier(){
        val startingPosition = currentPosition-1
        while(peek().isLetter()){
            advance()
                }
        val literal :String = input.substring(startingPosition,currentPosition)
        if (literal=="let"){
            tokens.add(Token(TokenType.LET))

        }else{
            tokens.add(Token(TokenType.IDENTIFIER,literal))
        }
    }
    private fun scanNextToken(){
        val currentCharacter :Char = advance()
        if (currentCharacter == '('){
            tokens.add(Token(TokenType.OPEN_PARENTHESIS))
        }
        else if (currentCharacter == ')'){
            tokens.add(Token(TokenType.CLOSE_PARENTHESIS))
        }
        else if (currentCharacter == '+'){
            tokens.add(Token(TokenType.PLUS))

        }
        else if (currentCharacter == '-'){
            tokens.add(Token(TokenType.MINUS))
        }
        else if (currentCharacter == '*'){
            tokens.add(Token(TokenType.STAR))}
        else if (currentCharacter == '/'){
            tokens.add(Token(TokenType.SLASH))
        }
        else if (currentCharacter.isDigit()){
            scanNumber()
        }
        else if (currentCharacter == ' '|| currentCharacter=='\n' || currentCharacter=='\r' || currentCharacter=='\t'){

        }
        else if (currentCharacter == '='){
            tokens.add(Token(TokenType.EQUAL))

        }
        else if (currentCharacter == ';'){
            tokens.add(Token(TokenType.SEMICOLON))
        }
        else if (currentCharacter.isLetter()){
            scanIdentifier()
        }
        else{
            error("Unexpected character '$currentCharacter'")
    }

}}
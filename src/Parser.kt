import java.sql.Statement

class Parser(private  val tokens: List<Token>) {
    private var currentPosition :Int = 0
    private fun peek():Token{
        return tokens[currentPosition]
    }
    fun parse(): List<Stmt> {
        val statements= mutableListOf<Stmt>()
        while (!isAtEnd()){
            statements.add(parseStatement())
        }
        consume(TokenType.END_OF_FILE,"Expected end of file")
        return statements
    }
    private fun parseStatement(): Stmt {
        if (match(TokenType.LET)){
            return parseVarDeclaration()
        }
        return parseExpressionStatement()
    }
    private  fun parseVarDeclaration(): Stmt{
        val name :String = consume(TokenType.IDENTIFIER,"Expected identifier in variable").toString()
        consume(TokenType.EQUAL,"Expected equal to in variable")
        val initializer:Expr= parseExpression()
        consume(TokenType.SEMICOLON,"Expected semicolon in variable")
        return Stmt.VarDeclaration(name, initializer)
    }
    private fun parseExpressionStatement(): Stmt {
        val expression:Expr = parseExpression()
        consume(TokenType.SEMICOLON,"Expected semicolon in expression")
        return Stmt.ExpressionStmt(expression)
    }
    private fun parseTerm():Expr{
        var workingExpression :Expr = parseFactor()
        while (match(TokenType.STAR,TokenType.SLASH)){
            val operator = previous()
            val parsedFactor = parseFactor()
            workingExpression = Expr.Binary(workingExpression,operator,parsedFactor)
        }
        return workingExpression

    }
    private fun parseExpression():Expr{
        var workingExpression :Expr = parseTerm()
        while (match(TokenType.PLUS,TokenType.MINUS)) {
            val operator:Token = previous()
            val parsedTerm :Expr =parseTerm()
            workingExpression= Expr.Binary(workingExpression,operator,parsedTerm)
        }
        return workingExpression
    }
    private  fun parseFactor():Expr{
        if (match(TokenType.NUMBER)){
            return Expr.NumberLiteral(previous().literal.toInt())
        }
        if (match(TokenType.OPEN_PARENTHESIS)){
            val parsedExpression:Expr = parseExpression()
            consume(TokenType.CLOSE_PARENTHESIS,"Failed")
            return parsedExpression
        }
        if (match(TokenType.IDENTIFIER)){
            return Expr.Variable(previous().literal)
        }
        error("Unable to parse factor , expected number or open-parenthesis expression")
    }
    private fun isAtEnd():Boolean{
        return peek().type == TokenType.END_OF_FILE

    }
    private fun advance() : Token{
        if (!isAtEnd()){
            currentPosition=currentPosition+1
        }
        return previous()
    }
    private fun previous():Token{
        return tokens[currentPosition-1]
    }
    private fun check(type:TokenType): Boolean{
        if (isAtEnd()){
            return type==TokenType.END_OF_FILE
        }
        return peek().type == type
    }
    private fun consume(type:TokenType,message:String):Token{
        if (check(type)){
            return advance()
        }
        error(message)
    }
    private  fun match(vararg type : TokenType): Boolean {
        for (type in type){
            if (check(type)){
                advance()
                return true

            }

        }
        return false
    }
}
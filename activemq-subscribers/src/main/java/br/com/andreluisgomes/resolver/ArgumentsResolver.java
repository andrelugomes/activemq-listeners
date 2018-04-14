package br.com.andreluisgomes.resolver;

/**
 * Created by andre on 15/05/17.
 */
public class ArgumentsResolver {

    private String argumentName;

    public ArgumentsResolver(String[] args) {
        try {
            argumentName = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro ao recuperar o nome do argumento. Error=" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getArgumentName() {
        return argumentName;
    }
}

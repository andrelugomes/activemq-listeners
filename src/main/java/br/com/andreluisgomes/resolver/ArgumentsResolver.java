package br.com.andreluisgomes.resolver;

/**
 * Created by andre on 15/05/17.
 */
public class ArgumentsResolver {

    private String arqumentName;

    public ArgumentsResolver(String[] args) {
        try {
            arqumentName = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro ao recuperar o nome do argumento.");
            throw new RuntimeException(e.getMessage());
        }
    }


    public String getArqumentName() {
        return arqumentName;
    }
}

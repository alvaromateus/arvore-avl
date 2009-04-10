package AVL;
import java.io.*;
import java.lang.String;

public class LeArquivo {
    BufferedReader txt;
    BufferedWriter txtNovo;
        
    public LeArquivo() throws IOException {                
    }    
    
    public String le(String arquivo) throws Exception {
        try {                        
            txt = new BufferedReader(new FileReader(arquivo));
            String valor;
            String arquivoLido = new String();             
            valor = txt.readLine();            
            while (valor != null) {
                arquivoLido += valor;                                
                valor = txt.readLine();
            }
            txt.close();
            return arquivoLido;
        } catch (IOException e) {
            System.out.println("Erro em leitura de arquivo");
            return null;
        }
    }            
    
    public void grava (String arquivo, String valor) throws Exception {
        try  {
            txtNovo = new BufferedWriter(new FileWriter(arquivo));            
            String arquivoGravado = new String();
            txtNovo.write(valor + "\t");                                                                        
            txtNovo.close();                            
        } catch (IOException e) {
            System.out.println("Erro em leitura de arquivo");
        }
    }    
    
    public void limpa(String arquivo) throws Exception {
        try  {
            txtNovo = new BufferedWriter(new FileWriter(arquivo));
            txtNovo.write("");
            txtNovo.close();
            txtNovo.flush();
        } catch (IOException e) {
            System.out.println("Erro em leitura de arquivo");
        }
    }
    
    public String nome(int codigo) {
        String arquivo="";
        try {
            arquivo = this.le("cliente.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int i=0;        
        while (!arquivo.substring(i, i+8).equals("Codigo:" + codigo))
            i++;        
        int inicio = i+15;
        i = inicio;
        while (!arquivo.substring(i, i+7).equals("Codigo:") || (arquivo.length() < 20))
            i++;
        int fim = i;
        String teste = arquivo.substring(inicio, fim);
        return teste;
    }        
}
package Elements;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class Tabela extends AbstractTableModel{

    private String[] nomeDaColuna;
    private List<Produto> listaDeProdutos;

    public Tabela(List<Produto> produtos){
        nomeDaColuna = new String[4];
        nomeDaColuna[0] = "Nome";
        nomeDaColuna[1] = "Preço (R$)";
        nomeDaColuna[2] = "Peso (g)";
        nomeDaColuna[3] = "Estoque";

        // ALIAS
        produtos = listaDeProdutos;

    }

    @Override
    public int getRowCount() {
        return (listaDeProdutos != null) ? listaDeProdutos.size() : 0;

    }

    @Override
    public int getColumnCount() {
        return nomeDaColuna.length;

    }

    @Override
    public String getColumnName(int column) {
        return nomeDaColuna[column];

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 0:
                listaDeProdutos.get(rowIndex).getName();
        
            case 1:
                listaDeProdutos.get(rowIndex).getPrice();

            case 2:
                listaDeProdutos.get(rowIndex).getWeight();

            case 3:
                listaDeProdutos.get(rowIndex).getStock();

            default:
                return 0;

        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        switch(columnIndex){
            case 0:
                return String.class;
        
            case 1:
                return Float.class;

            case 2:
                return Integer.class;

            case 3:
                return Integer.class;

            default:
                return null;

        }

    }
    
}

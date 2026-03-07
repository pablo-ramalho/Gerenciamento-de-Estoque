package Elements;

public class Produto implements Comparable<Produto>{

    private String name;
    private Float price;
    private Integer weight;
    private Integer stock;

    /**
     * Construtor de inicialização da classe Produto
     * @param name o nome do produto
     * @param price o preço do produto
     * @param weight o peso do produto (em gramas)
     * @param stock o estoque disponível do produto
     */
    public Produto(String nome, Float preco, Integer peso, Integer estoque){
        name = nome;
        price = preco;
        weight = peso;
        stock = estoque;

    }

    public Integer getWeight() {
        return weight;
    }

    public String getName(){
        return name;
    }

    public Float getPrice(){
        return price;
    }

    public Integer getStock(){
        return stock;
    }

    @Override
    public String toString(){
        return 
        "Nome do produto: " + getName() + "\n" +
        "Preço: " + getPrice() + "\n" +
        "Peso:" + getWeight() + "\n" +
        "Estoque: " + getStock() + "\n";
    }

    /**
     * Classifica os objetos pelo preço
     * @param produto o produto que terá um atributo comparado com o atributo desta classe
     * @return
     */
    @Override
    public int compareTo(Produto produto){
        return (price < produto.price) ? -1 : (price > produto.price ? 1 : 0);


    }
    
}

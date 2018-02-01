package co.matisses.jasperbeans;

/**
 *
 * @author jguisao
 */
public class ProductoMercadolibreBean {

    private String pictureMini;
    private String referencia;
    private String resultado;

    public ProductoMercadolibreBean() {
    }

    public ProductoMercadolibreBean(String pictureMini, String referencia, String resultado) {
        this.pictureMini = pictureMini;
        this.referencia = referencia;
        this.resultado = resultado;
    }

    public String getPictureMini() {
        return pictureMini;
    }

    public void setPictureMini(String pictureMini) {
        this.pictureMini = pictureMini;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
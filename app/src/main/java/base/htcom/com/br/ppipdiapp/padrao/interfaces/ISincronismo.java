package base.htcom.com.br.ppipdiapp.padrao.interfaces;

public interface ISincronismo {
			
	public String getTabela();
	public String getCampos();
	public String getTag();
	public String getWhere(String dtUltSincronizacao);
}
package base.htcom.com.br.ppipdiapp.async;

public interface TarefaInterface {
	void respostaAsync(String json);

	void respostaAsyncEnvioUpload(String json);

	void respostaAsyncConfirmaRecOs(String json);

	void respostaAsyncConfirmaEnvOs(String json);
}

package base.htcom.com.br.ppipdiapp.async;

public interface TarefaInterfaceEnviar {
	void respostaAsyncEnvioArqPref(String json);
	void respostaAsyncEnvioCarregamento(String json);
	void respostaAsyncEnvioBateria(String json);
	void respostaAsyncConfirmaEnvOs(String json);
}

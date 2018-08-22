package base.htcom.com.br.ppipdiapp.async;

public interface TarefaInterfaceRev {
	void respostaAsync(String json);
	void respostaAsyncRecRevArqPref(String json) throws Exception;
	void respostaAsyncRecRevCarregamento(String json) throws Exception;
	void respostaAsyncConfirmaRecOs(String json) throws Exception;
	void respostaAsyncVerificarRev(String json) throws Exception;
	
	

}

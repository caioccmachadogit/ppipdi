package base.htcom.com.br.ppipdiapp.async;

import java.util.List;

import base.htcom.com.br.ppipdiapp.model.ControleUpload;

public interface TarefaInterfaceReenv {
	void respostaAsyncEnvioArqPref(String json);
	void respostaAsyncEnvioCarregamento(String json);
	void respostaAsyncEnvioBateria(String json);
	void respostaAsyncEnvioUpload(List<ControleUpload> controleUploadsSucesso);

}

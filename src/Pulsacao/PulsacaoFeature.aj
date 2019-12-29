package Pulsacao;

public aspect PulsacaoFeature {

	before(): call(* *.operations2()){
		new PulsacaoAssistantZirk();
	} 
}

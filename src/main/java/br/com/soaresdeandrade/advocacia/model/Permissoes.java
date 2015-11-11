package br.com.soaresdeandrade.advocacia.model;

public enum Permissoes {
	CADASTRO_USUARIO("cad_user"), CADASTRO_PERFIL("cad_perfil");
	
	private String permissao;
	public static final String PREFIX = "PERMISSAO.";

	private Permissoes(String permissao) {
		this.permissao = permissao;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}

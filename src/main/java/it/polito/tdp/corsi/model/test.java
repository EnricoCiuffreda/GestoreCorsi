package it.polito.tdp.corsi.model;

public class test {

	public static void main(String[] args) {
		Model model=new Model();
		System.out.println(model.getCorsiByPeriodo(1));
		System.out.println(model.getIscrittiByPeriodo(1));
		System.out.println(model.esistecorso(new Corso("bjagdbjg",null,null,null)));

	}

}

package producer;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

import dto.Hoge;

@Stateless
public class HogeProducer {

	@Produces
	public Hoge createHoge() {

		Hoge hoge = new Hoge();
		hoge.setHoge("ほげ");

		return hoge;

	}
}

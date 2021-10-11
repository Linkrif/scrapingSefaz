package com.link.scrapingjava.MonitorSefaz;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect(
				"https://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx?versao=0.00&tipoConteudo=Skeuqr8PQBY=")
				.get();
		log(doc.title());
		String disponivel = "Online";
		String indisponivel = "Servico Indisponivel";
		String falha = "Servico com falhas";

		Elements body = doc.select("#ctl00_ContentPlaceHolder1_gdvDisponibilidade2");
		for (Element e : body.select("tr")) {
			Element link = e.select("tr").first();
			Iterator<Element> ite = link.select("tr").iterator();
		//	String a = link.select("tr").get(0).siblingElements().text();
			
			// Element link = e.select("tr").first();

			String disponibilidadeImpar = e.select("tr.linhaImparCentralizada img").attr("src");
			String disponibilidadePar = e.select("tr.linhaParCentralizada img").attr("src");

			/* System.out.println(valor + ite.next().text()); */
			
			System.out.println(ite.next().text());

			if (disponibilidadeImpar.equals("imagens/bola_verde_P.png")
					|| disponibilidadePar.equals("imagens/bola_verde_P.png")) {
				System.out.println(String.format(disponivel));
			}
			if (disponibilidadeImpar.equals("imagens/bola_vermelho_G.png")
					|| disponibilidadePar.equals("imagens/bola_vermelho_G.png")) {
				System.out.println(String.format(indisponivel));
			}
			if (disponibilidadeImpar.equals("imagens/bola_amarela_G.png")
					|| disponibilidadePar.equals("imagens/bola_amarela_G.png")) {
				System.out.println(String.format(falha));
			}

		}

		
	}

		/*
		 * System.out.println(body.select("tr").size()); Elements newsHeadlines =
		 * doc.select("#ctl00_ContentPlaceHolder1_gdvDisponibilidade2"); for (Element
		 * headline : newsHeadlines) { log("%s\n\t%s",
		 * headline.attr("linhaImparCentralizada"), headline.absUrl("src")); }
		 */
	
	private static void log(String msg, String... vals) {
		System.out.println(String.format(msg, vals));
	}
}

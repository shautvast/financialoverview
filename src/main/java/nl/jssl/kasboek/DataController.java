/*
 * Project: Wehkamp RIP
 * Copyright (c) 2013-2014 Wehkamp B.V.
 *
 * Versie : $LastChangedRevision: $
 * Datum  : $LastChangedDate: $
 * Door   : $LastChangedBy: $
 */
package nl.jssl.kasboek;

import java.util.List;

import nl.jssl.kasboek.BestandReader.Transactie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {

	@RequestMapping(value = "/transacties", method = RequestMethod.GET)
	public @ResponseBody List<Transactie> getTransacties() {
		return new BestandReader().lees("/overzicht.txt");
	}
}

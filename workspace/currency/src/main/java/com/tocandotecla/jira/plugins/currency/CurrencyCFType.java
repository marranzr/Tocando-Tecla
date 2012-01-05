package com.tocandotecla.jira.plugins.currency;

import java.util.Currency;
import java.util.Map;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.converters.DoubleConverter;
import com.atlassian.jira.issue.customfields.impl.NumberCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;

/**
 * A custom field type that displays numbers as the local currency.
 * The transport object is a Double, just like the parent class
 */
public class CurrencyCFType extends NumberCFType{

	public CurrencyCFType(	final CustomFieldValuePersister customFieldValuePersister,
							final DoubleConverter doubleConverter,
							final GenericConfigManager genericConfigManager) {
		super(customFieldValuePersister,
			  doubleConverter,
			  genericConfigManager);	
	}
	
	public Map<String, Object> getVelocityParameters (final Issue issue,
													  final CustomField field,
													  final FieldLayoutItem fieldLayoutItem) {
		final Map<String, Object> map = super.getVelocityParameters(issue, field, fieldLayoutItem);
		String symbol = Currency.getInstance(getI18nBean().getLocale()).getSymbol();
		map.put("currencySymbol", symbol);
		log.debug("Currency symbol is " + symbol);
		return map;
	}
}

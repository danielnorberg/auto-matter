package io.norberg.automatter.gson;


/**
 * A mechanism for providing custom field naming in Gson adapted for strings
 * This allows the client code to translate field names into a particular convention
 * that is not supported as a normal Java field declaration rules.
 * For example, Java does not support"-"characters in a field name.
 *
 * @author Olle Lundberg
 */

public interface StringFieldNamingStrategy {

  /**
   * Translates the string into its JSON field name representation.
   *
   * @param f the string object that we are translating
   * @return the translated string.
   */
  public String translateName(String f);
}
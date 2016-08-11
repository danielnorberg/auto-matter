/*
* Copyright (C) 2008 Google Inc.
* Copyright (C) 2016 Olle Lundberg.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package io.norberg.automatter.gson;

import java.util.Locale;

/**
 * An enumeration that defines a few standard naming conventions for JSON field names.
 * This enumeration is a reimplementation of the FieldNamingPolicy shipped with Gson, but
 * adapted to work on strings instead of fields.
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 * @author Olle Lundberg
 */
public enum StringFieldNamingPolicy implements StringFieldNamingStrategy {

  /**
   * Using this naming policy with Gson will ensure that the field name is
   * unchanged.
   */
  IDENTITY() {
    @Override
    public String translateName(String f) {
      return f;
    }
  },

  /**
   * Using this naming policy will ensure that the first "letter" of the string
   * is capitalized when translated.
   *
   * <p>Here's a few examples of the form "Java Field Name" ---> "JSON Field Name":</p>
   * <ul>
   * <li>someFieldName ---> SomeFieldName</li>
   * <li>_someFieldName ---> _SomeFieldName</li>
   * </ul>
   */
  UPPER_CAMEL_CASE() {
    @Override
    public String translateName(String f) {
      return upperCaseFirstLetter(f);
    }
  },

  /**
   * Using this naming policy will ensure that the first "letter" of the string
   * is capitalized when translated and the words will be separated by a space.
   *
   * <p>Here's a few examples of the form "Java Field Name" ---> "JSON Field Name":</p>
   * <ul>
   * <li>someFieldName ---> Some Field Name</li>
   * <li>_someFieldName ---> _Some Field Name</li>
   * </ul>
   *
   */
  UPPER_CAMEL_CASE_WITH_SPACES() {
    @Override
    public String translateName(String f) {
      return upperCaseFirstLetter(separateCamelCase(f, " "));
    }
  },

  /**
   * Using this naming policy will modify the string from its camel cased
   * form to a lower case string where each word is separated by an underscore (_).
   *
   * <p>Here's a few examples of the form "Java Field Name" ---> "JSON Field Name":</p>
   * <ul>
   * <li>someFieldName ---> some_field_name</li>
   * <li>_someFieldName ---> _some_field_name</li>
   * <li>aStringField ---> a_string_field</li>
   * <li>aURL ---> a_u_r_l</li>
   * </ul>
   */
  LOWER_CASE_WITH_UNDERSCORES() {
    @Override
    public String translateName(String f) {
      return separateCamelCase(f, "_").toLowerCase(Locale.ENGLISH);
    }
  },

  /**
   * Using this naming policy will modify the string from its camel cased
   * form to a lower case string where each word is separated by a dash (-).
   *
   * <p>Here's a few examples of the form "Java Field Name" ---> "JSON Field Name":</p>
   * <ul>
   * <li>someFieldName ---> some-field-name</li>
   * <li>_someFieldName ---> _some-field-name</li>
   * <li>aStringField ---> a-string-field</li>
   * <li>aURL ---> a-u-r-l</li>
   * </ul>
   * Using dashes in JavaScript is not recommended since dash is also used for a minus sign in
   * expressions. This requires that a field named with dashes is always accessed as a quoted
   * property like {@code myobject['my-field']}. Accessing it as an object field
   * {@code myobject.my-field} will result in an unintended javascript expression.
   *
   */
  LOWER_CASE_WITH_DASHES() {
    @Override
    public String translateName(String f) {
      return separateCamelCase(f, "-").toLowerCase(Locale.ENGLISH);
    }
  };

  /**
   * Converts the string that uses camel-case define word separation into
   * separate words that are separated by the provided {@code separatorString}.
   */
  static String separateCamelCase(String name, String separator) {
    StringBuilder translation = new StringBuilder();
    for (int i = 0; i < name.length(); i++) {
      char character = name.charAt(i);
      if (Character.isUpperCase(character) && translation.length() != 0) {
        translation.append(separator);
      }
      translation.append(character);
    }
    return translation.toString();
  }

  /**
   * Ensures the JSON field names begins with an upper case letter.
   */
  static String upperCaseFirstLetter(String name) {
    StringBuilder fieldNameBuilder = new StringBuilder();
    int index = 0;
    char firstCharacter = name.charAt(index);

    while (index < name.length() - 1) {
      if (Character.isLetter(firstCharacter)) {
        break;
      }

      fieldNameBuilder.append(firstCharacter);
      firstCharacter = name.charAt(++index);
    }

    if (index == name.length()) {
      return fieldNameBuilder.toString();
    }

    if (!Character.isUpperCase(firstCharacter)) {
      String modifiedTarget = modifyString(Character.toUpperCase(firstCharacter), name, ++index);
      return fieldNameBuilder.append(modifiedTarget).toString();
    } else {
      return name;
    }
  }

  private static String modifyString(char firstCharacter, String srcString, int indexOfSubstring) {
    return (indexOfSubstring < srcString.length())
           ? firstCharacter + srcString.substring(indexOfSubstring)
           : String.valueOf(firstCharacter);
  }
}


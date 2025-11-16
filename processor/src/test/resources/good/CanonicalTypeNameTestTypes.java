package foo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Test class for exercising getCanonicalTypeName with various type scenarios.
 */
public interface CanonicalTypeNameTestTypes {

  // Simple types
  String simpleString();
  Integer simpleInteger();

  // Parameterized types
  List<String> listOfStrings();
  Set<Integer> setOfIntegers();
  Map<String, Integer> mapOfStringToInteger();

  // Nested parameterized types
  List<List<String>> nestedList();
  Map<String, List<Integer>> mapWithListValue();

  // Types with JSpecify TYPE_USE annotations
  @Nullable String nullableString();
  @Nullable List<String> nullableList();
  @Nullable Map<String, Integer> nullableMap();
  List<@Nullable String> listOfNullables();
}

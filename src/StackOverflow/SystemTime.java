package StackOverflow;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SystemTime {
  public static String time() {

    return new Date().toString();
  }

  public SystemTime() {}

  public String toString() {

    return "SystemTime{}";
  }
}

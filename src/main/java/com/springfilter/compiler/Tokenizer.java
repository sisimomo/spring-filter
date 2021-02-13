package com.springfilter.compiler;

import java.util.LinkedList;

import com.springfilter.compiler.exception.InvalidInputException;
import com.springfilter.compiler.exception.TokenizerException;
import com.springfilter.compiler.token.IToken;
import com.springfilter.compiler.token.Matcher;

public class Tokenizer {

  private Tokenizer() {}

  public static LinkedList<IToken> tokenize(Matcher<?>[] matchers, String input) throws TokenizerException {

    LinkedList<IToken> tokens = new LinkedList<>();

    StringBuilder sb = new StringBuilder(input);

    while (sb.length() > 0) {

      boolean consumed = false;

      int currentLength = sb.length();

      for (Matcher<?> matcher : matchers) {

        IToken token = matcher.match(sb);

        if (sb.length() != currentLength) {

          if (token != null) {
            tokens.add(token);
          }

          consumed = true;
          break;

        }

      }

      if (!consumed) {
        throw new InvalidInputException(sb.toString());
      }

    }

    return tokens;

  }

}
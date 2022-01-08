package ru.otus.elena363404.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOStreamService implements IOService {
  private final PrintStream out;
  private final Scanner sc;

  public IOStreamService(@Value("#{T(java.lang.System).out}") PrintStream out,
                         @Value("#{T(java.lang.System).in}") InputStream in) {
    this.out = out;
    this.sc = new Scanner(in);
  }

  @Override
  public void out(String message) {
    out.println(message);
  }

  @Override
  public int readInt() {
    return sc.nextInt();
  }

  @Override
  public String readString() {
    return sc.next();
  }

  @Override
  public String getInputId() {
    String id;

    String input = readString();
    return input;
  }
}

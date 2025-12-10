import { render, screen } from "@testing-library/react";
import LoginForm from "../components/login-form";
import { test, expect } from "vitest";

test("zeigt Username, Passwort und Login-Button an", () => {
  render(<LoginForm />);

  // Labels im Formular
  expect(screen.getByLabelText(/Benutzername/i)).toBeInTheDocument(); // passt zu "Benutzername oder Email"
  expect(screen.getByLabelText(/Passwort/i)).toBeInTheDocument();
  expect(
    screen.getByRole("button", { name: /Einloggen/i })
  ).toBeInTheDocument();
});

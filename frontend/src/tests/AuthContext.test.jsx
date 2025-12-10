import { render, screen } from "@testing-library/react";
import { AuthProvider, useAuth } from "../contexts/AuthContext";
import { test, expect, beforeEach } from "vitest";

// Test-Komponente
function TestComponent() {
  const { user } = useAuth();
  return <div>{user ? `Logged in as ${user.username}` : "Not logged in"}</div>;
}

beforeEach(() => {
  // User in localStorage mocken
  localStorage.setItem("authToken", "fakeToken");
  localStorage.setItem("userData", JSON.stringify({ username: "testuser" }));
});

test("zeigt den eingeloggten User an", () => {
  render(
    <AuthProvider>
      <TestComponent />
    </AuthProvider>
  );

  expect(screen.getByText(/Logged in as testuser/i)).toBeInTheDocument();
});

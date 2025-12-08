import React from "react";
import Navigation from "./navigation";
import { Outlet } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import { Button } from "./button";

export const Layout = () => {
  const { isAuthenticated, user, logout } = useAuth();
  return (
    <div className="app">
      <header className="app-header">
        <Navigation />
        <h1 className="app-title">RailStock</h1>
        {isAuthenticated && (
          <div>
            <span>Hallo, {user.username}!</span>
            <Button onClick={logout}>Logout</Button>
          </div>
        )}
      </header>

      <main className="layout-main-content">
        <div className="page-container">
          <Outlet />
        </div>
      </main>

      <footer className="layout-footer">
        <p>© RailStock. All rights reserved</p>
        <p>Made with ❤️ by Nico Imesch</p>
      </footer>
    </div>
  );
};

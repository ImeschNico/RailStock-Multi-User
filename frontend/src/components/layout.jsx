import React from "react";
import Navigation from "./navigation";
import { Outlet } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import { Button } from "./button";

/**
 * Layout Component
 * Enthält Header, Footer und Main-Content (Outlet für Routen)
 *
 * @component
 * @returns {JSX.Element}
 */
export const Layout = () => {
  const { isAuthenticated, user } = useAuth();
  const hideLayout = window.location.pathname === "/login";
  return (
    <div className="app">
      {!hideLayout && (
        <header className="app-header">
          <Navigation />
          <h1 className="app-title">RailStock</h1>
          {isAuthenticated && (
            <div>
              <span>Hallo, {user.username}!</span>
            </div>
          )}
        </header>
      )}

      <main className="layout-main-content">
        <div className="page-container">
          <Outlet />
        </div>
      </main>

      {!hideLayout && (
        <footer className="layout-footer">
          <p>© RailStock. All rights reserved</p>
          <p>Made with ❤️ by Nico Imesch</p>
        </footer>
      )}
    </div>
  );
};

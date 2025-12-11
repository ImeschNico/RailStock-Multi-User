import React from "react";

/**
 * Button Component
 * Allgemeine Button-Komponente für die Anwendung.
 *
 * @param {string} text - Text des Buttons (optional, wenn keine Children gesetzt)
 * @param {React.ReactNode} children - Kinder-Elemente für den Button
 * @param {function} onAnswerClick - Callback beim Klick
 * @param {boolean} disabled - Button deaktivieren
 * @param {string} className - CSS-Klasse für Styling
 * @component
 * @returns {JSX.Element}
 */
export const Button = ({
  text,
  children,
  onAnswerClick,
  disabled = false,
  className,
  ...props
}) => {
  return (
    <button
      className={`button ${className || ""}`}
      onClick={onAnswerClick}
      disabled={disabled}
      {...props}
    >
      {children || text}
    </button>
  );
};

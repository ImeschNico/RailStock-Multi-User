import React from "react";

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

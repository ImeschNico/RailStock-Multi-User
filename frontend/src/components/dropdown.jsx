import React from "react";

export const Dropdown = ({ label, value, onChange, options, className }) => {
  return (
    <div>
      <label>{label}: </label>
      <select
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className={className}
      >
        <option value="">---Alle---</option>
        {options.map((opt) => (
          <option key={opt} value={opt}>
            {opt}
          </option>
        ))}
      </select>
    </div>
  );
};

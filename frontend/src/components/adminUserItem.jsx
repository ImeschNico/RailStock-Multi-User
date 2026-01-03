export default function AdminUserItem({ user, onChangeRole }) {
  return (
    <li className="admin-user-item">
      <span>
        {user.username} ({user.role})
      </span>
      <select
        value={user.role}
        onChange={(e) => onChangeRole(user.id, e.target.value)}
      >
        <option value="USER">USER</option>
        <option value="ADMIN">ADMIN</option>
      </select>
    </li>
  );
}

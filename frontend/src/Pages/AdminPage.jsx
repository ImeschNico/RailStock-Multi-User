import { useEffect, useState } from "react";
import { getAllUsers, changeUserRole } from "../services/adminUser-service";
import AdminUserList from "../components/adminUserList";

export default function AdminPage() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    try {
      const res = await getAllUsers();
      setUsers(res.data);
    } catch {
      setError("Fehler beim Laden der User");
    } finally {
      setLoading(false);
    }
  };

  const onChangeRole = async (userId, role) => {
    try {
      await changeUserRole(userId, role);
      setUsers((prev) =>
        prev.map((u) => (u.id === userId ? { ...u, role } : u))
      );
    } catch {
      alert("Rolle konnte nicht geändert werden");
    }
  };

  if (loading) return <p>Lade Benutzer...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="admin-container">
      <h1>Admin – Benutzerverwaltung</h1>
      <AdminUserList users={users} onChangeRole={onChangeRole} />
    </div>
  );
}

export default async function delete_institute(id) {
    try {
        const res = await fetch(`${import.meta.env.VITE_INSTITUTE_ENDPOINT}/${id}`, {
            method: "DELETE",
        });
        return res.ok;
    } catch (err) {
        console.error("Delete failed:", err);
        return false;
    }
}

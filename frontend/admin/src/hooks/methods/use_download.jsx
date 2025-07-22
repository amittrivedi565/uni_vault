export const handleDownload = async (id) => {
  try {
    const res = await fetch(`http://localhost:8010/api/download/${id}`);

    if (!res.ok) throw new Error("Download failed");

    const blob = await res.blob();
    const url = window.URL.createObjectURL(blob);

    const a = document.createElement("a");
    a.href = url;
    a.download = `document_${id}.pdf`;
    a.click();

    window.URL.revokeObjectURL(url);
  } catch (err) {
    console.error("Download error:", err.message);
    alert("Failed to download file.");
  }
};

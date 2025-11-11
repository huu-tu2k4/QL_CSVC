// src/components/history/ExportReportButton.jsx
export default function ExportReportButton({ data = [] }) {
  const exportCSV = () => {
    const csv = [
      ['Mã LS', 'Sự kiện', 'Thiết bị', 'Ngày', 'Người thực hiện', 'Chi tiết'],
      ...data.map(e => [
        e.code,
        e.title,
        e.details?.find(d => d.label === 'Thiết bị')?.value || '',
        e.date,
        e.details?.find(d => d.label === 'Người thực hiện')?.value || '',
        e.description
      ])
    ].map(row => row.join(',')).join('\n');

    const blob = new Blob(['\uFEFF' + csv], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `lich_su_thiet_bi_${new Date().toISOString().slice(0,10)}.csv`;
    link.click();
  };

  return (
    <button className="btn btn-primary d-flex align-items-center gap-2" onClick={exportCSV}>
      <i className="bi bi-download"></i>
      Xuất báo cáo
    </button>
  );
}
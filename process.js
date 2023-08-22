document.addEventListener("DOMContentLoaded", function () {
    const submitBtn = document.getElementById("submitBtn");
    submitBtn.addEventListener("click", savebookingData);
});

function savebookingData() {
    const Name = document.getElementById("Name").value;
    const mviName = document.getElementById("mviName").value;
    const mviDate = document.getElementById("mviDate").value;
    const seats = document.getElementById("seats").value;
    const classType = document.getElementById("class").value;

    const xmlString = `
        <booking>
            <Name>${Name}</Name>
            <mviName>${mviName}</mviName>
            <mviDate>${mviDate}</mviDate>
            <seats>${seats}</seats>
            <class>${classType}</class>
        </booking>
    `;

    const blob = new Blob([xmlString], { type: "application/xml" });
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = "booking.xml";
    link.click();
}
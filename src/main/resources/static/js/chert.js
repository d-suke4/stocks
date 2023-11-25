/* ランダムな色を生成する関数 */
function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

/* Chart.jsのコード */
document.addEventListener('DOMContentLoaded', function () {

    var ctx = document.getElementById('myPieChart').getContext('2d');
    var backgroundColors = data.values.map(function () {
        return getRandomColor();
    });

    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: data.labels,
            datasets: [{
                data: data.values,
                backgroundColor: backgroundColors,
                borderWidth: 1
            }]
        }
    });
});
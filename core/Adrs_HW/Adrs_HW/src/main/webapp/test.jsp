<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOAST UI Grid Test</title>
<link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
</head>
<body>
    <div class="container" style="padding: 20px;">
        <h1>주소록 목록 테스트</h1>
        <hr>
        <div id="grid"></div>
    </div>

    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function() {
            const gridData = [ 
                { name: '홍길동', artist: '길동아티스트', type: '개인', release: '2025-09-18', genre: '주소록' }, 
                { name: '이순신', artist: '장군아티스트', type: '공용', release: '2025-09-19', genre: '주소록' } 
            ];

            try {
                const grid = new tui.Grid({
                    el: document.getElementById('grid'),
                    data: gridData,
                    scrollX: false,
                    scrollY: false,
                    bodyHeight: 200,
                    columns: [ 
                        { header: '이름', name: 'name' }, 
                        { header: '아티스트', name: 'artist' }, 
                        { header: '구분', name: 'type' }, 
                        { header: '날짜', name: 'release' }, 
                        { header: '장르', name: 'genre' } 
                    ]
                });
                console.log("그리드 생성 성공!");
            } catch (e) {
                console.error("그리드 생성 중 에러 발생:", e);
            }
        });
    </script>
</body>
</html>
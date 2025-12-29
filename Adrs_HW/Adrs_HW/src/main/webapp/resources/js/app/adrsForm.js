/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {

	class ButtonRenderer {
		constructor(props) {
			const el = document.createElement('button');
			el.innerText = '주소록에 추가';
			el.className = 'btn btn-sm btn-info';
			el.addEventListener('click', () => {
				const rowData = props.grid.getRow(props.rowKey);
				console.log(rowData);

				$.ajax({
					url: '/adrs/insert',
					method: 'POST',
					data: {
						memName: rowData.memName,
						memHp: rowData.memHp,
						memAdd1: rowData.memAdd1,
						memMail: rowData.memMail,
					},
					success: function(data) {
						alert("주소록에 추가가 성공했습니다.");
					},
					error: function(xhr, status, error) {
						console.error("데이터 로드 실패:", error);
						alert("로그인이 되어있는지 확인해주세요.");
					}
				});
			});
			this.el = el;
		}
		getElement() {
			return this.el;
		}
	}

	const memberGrid = new tui.Grid({
		el: document.getElementById('grid'),
		data: [], // 초기 데이터는 빈 배열
		scrollX: false,
		scrollY: false,
		bodyHeight: 400,
		columns: [
			{ header: '이름', name: 'memName' },   // MemberDTO 필드명에 맞춤
			{ header: '아이디', name: 'memId' },
			{ header: '연락처', name: 'memHp' },
			{ header: '이메일', name: 'memMail' },
			{ header: '주소', name: 'memAdd1' },
			{
				header: '관리',
				name: 'manage',
				renderer: { type: ButtonRenderer } // 💡 위에서 만든 렌더러 연결
			}
		]
	});


	$.ajax({
		url: '/member/list',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
			console.log("서버에서 받은 데이터:", data);
			memberGrid.resetData(data);
		},
		error: function(xhr, status, error) {
			console.error("데이터 로드 실패:", error);
			alert("데이터를 불러오는 중 오류가 발생했습니다.");
		}
	});
});
/**
 * 
 */

document.addEventListener('DOMContentLoaded', function() {

	class ButtonRenderer {
		constructor(props) {
			// 버튼들을 감쌀 컨테이너 생성
			const container = document.createElement('div');
			container.style.display = 'flex';
			container.style.gap = '5px';
			const editBtn = document.createElement('button');
			editBtn.innerText = '수정';
			editBtn.className = 'btn btn-sm btn-warning';


			editBtn.addEventListener('click', () => {
				const rowData = props.grid.getRow(props.rowKey);
				console.log(rowData);
				this.handleUpdate(rowData);
			});


			const deleteBtn = document.createElement('button');
			deleteBtn.innerText = '삭제';
			deleteBtn.className = 'btn btn-sm btn-danger';


			deleteBtn.addEventListener('click', () => {
				if (confirm("정말로 삭제하시겠습니까?")) {
					const rowData = props.grid.getRow(props.rowKey);
					console.log(rowData);
					this.handleDelete(rowData, props.grid);
				}
			});

			container.appendChild(editBtn);
			container.appendChild(deleteBtn);
			this.el = container;
		}
		handleUpdate(rowData) {
			$.ajax({
				url: '/adrs/update', // 수정 경로
				method: 'POST',
				data: {
					adrsNo: rowData.adrsNo, // 식별자 필수
					adrsName: rowData.adrsName,
					adrsTel: rowData.adrsTel,
					adrsAdd: rowData.adrsAdd,
					adrsMail: rowData.adrsMail,
				},
				success: function(resp) {
					alert(resp);
				},
				error: function(xhr) {
					alert("수정 실패: " + xhr.status);
				}
			});
		}

		handleDelete(rowData, grid) {
			$.ajax({
				url: '/adrs/delete',
				method: 'POST',
				data: { adrsNo: rowData.adrsNo },
				success: function(resp) {
					alert("삭제되었습니다.");
					grid.removeRow(rowData.rowKey);
				},
				error: function(xhr) {
					alert("삭제 권한이 없거나 오류가 발생했습니다."+ xhr.status + xhr.responseText);
				}
			});
		}

		getElement() {
			return this.el;
		}
	}

	const adrsGrid = new tui.Grid({
		el: document.getElementById('adrsGrid'),
		data: [], // 초기 데이터는 빈 배열
		scrollX: false,
		scrollY: false,
		bodyHeight: 400,
		columns: [
			{ header: 'index', name: 'adrsNo' },   // MemberDTO 필드명에 맞춤
			{ header: '이름', name: 'adrsName', editor: 'text' },
			{ header: '연락처', name: 'adrsTel', editor: 'text' },
			{ header: '이메일', name: 'adrsMail', editor: 'text' },
			{ header: '주소', name: 'adrsAdd', editor: 'text' },
			{
				header: '관리',
				name: 'manage',
				renderer: { type: ButtonRenderer } // 💡 위에서 만든 렌더러 연결
			}
		]
	});


	$.ajax({
		url: '/adrs/list',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
			console.log("서버에서 받은 데이터:", data);
			adrsGrid.resetData(data);
		},
		error: function(xhr, status, error) {
			console.error("데이터 로드 실패:", error);
			alert("데이터를 불러오는 중 오류가 발생했습니다.");
		}
	});
});
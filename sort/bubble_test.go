package sort

import "testing"

func TestBubbleSort(t *testing.T) {
	length := 20000
	list := getIntDate(length)
	bubbleSort(list, "desc")
	t.Log(list)
	if len(list) != length {
		t.Error("result length error")
	}
}

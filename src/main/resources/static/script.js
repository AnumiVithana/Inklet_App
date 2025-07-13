document.addEventListener("DOMContentLoaded", function () {
    const likeButtons = document.querySelectorAll(".like-btn");

    likeButtons.forEach(button => {
        button.addEventListener("click", function () {
            const postId = this.dataset.postId;
            const liked = this.dataset.liked === "true";
            const buttonEl = this;
            const likeTextEl = this.querySelector(".like-text");
            const likeCountEl = this.querySelector(".like-count");

            fetch(/like/${postId}, {
                method: "POST"
            })
        .then(res => res.json())
                .then(isNowLiked => {
                    // Update button state
                    buttonEl.dataset.liked = isNowLiked;

                    const count = parseInt(likeCountEl.innerText);
                    likeCountEl.innerText = isNowLiked ? count + 1 : count - 1;
                    likeTextEl.innerText = isNowLiked ? "Unlike" : "Like";
                })
                .catch(err => console.error("Error toggling like:", err));
        });
    });
})



document.addEventListener("DOMContentLoaded", function () {
    const textarea = document.getElementById("postContent");
    const charCount = document.getElementById("charCount");
    const charError = document.getElementById("charError");

    if (textarea) {
        textarea.addEventListener("input", () => {
            const length = textarea.value.length;
            charCount.textContent = ${length} / 500;

            if (length > 500) {
                charCount.classList.add("text-red-600");
                charError.classList.remove("hidden");
            } else {
                charCount.classList.remove("text-red-600");
                charError.classList.add("hidden");
            }
        });
    }
});
const postForm = document.querySelector("form[method='post']");

if (postForm) {
    postForm.addEventListener("submit", function (e) {
        if (textarea.value.length > 500) {
            e.preventDefault(); // Block submission
            alert("Your post exceeds the 500-character limit.");
        }
    });
}
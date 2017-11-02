package shared.forms


object Forms {
  type SubmitResponse[F,S] = Either[FormData[F],S]
}

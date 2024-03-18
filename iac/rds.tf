resource "aws_db_instance" "stop_run_away" {
  allocated_storage    = 20
  storage_type         = "gp2"
  engine               = "mysql"
  engine_version       = "8.0"
  instance_class       = "db.t2.micro"
  db_name              = "stoprunaway"
  username             = "stoprunaway"
  password             = ""
  skip_final_snapshot  = true
  parameter_group_name = aws_db_parameter_group.stop_run_away.name

  tags = {
    Name = "StopRunAwayDB"
  }
}

resource "aws_db_parameter_group" "stop_run_away" {
  name        = "stop-run-away-db-parameter-group"
  family      = "mysql8.0"

  parameter {
    name  = "time_zone"
    value = "Asia/Seoul"
  }

  parameter {
    name  = "character_set_client"
    value = "utf8mb4"
  }
  
  parameter {
    name  = "character_set_connection"
    value = "utf8mb4"
  }

  parameter {
    name  = "character_set_database"
    value = "utf8mb4"
  }

  parameter {
    name  = "collation_connection"
    value = "utf8mb4_general_ci"
  }
}